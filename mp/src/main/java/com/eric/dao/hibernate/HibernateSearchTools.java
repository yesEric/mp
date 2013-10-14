package com.eric.dao.hibernate;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.indexes.IndexReaderAccessor;

/**
 * Hibernate lucene  全文查找的工具类.
 *
 * @author Eric
 */
class HibernateSearchTools {
    protected static final Log log = LogFactory.getLog(HibernateSearchTools.class);

    /**
     * 根据查询名词生成一个lucence 查询
     * @param searchTerm 查询名词
     * @param searchedEntity 要查找的实体对象
     * @param sess hibernate session
     * @param defaultAnalyzer 解析查询语名词的缺省解析器
     * @return lucence 查询对象
     * @throws ParseException
     */
    public static Query generateQuery(String searchTerm, Class searchedEntity, Session sess, Analyzer defaultAnalyzer) throws ParseException {
        Query qry = null;

        if (searchTerm.equals("*")) {
        	// 如果是*，则生成全文查询对象
            qry = new MatchAllDocsQuery();
        } else {
            // 开始在索引的字段中进行查询

        	//声明索引读取器
            IndexReaderAccessor readerAccessor = null;
            IndexReader reader = null;
            try {
            	//创建全文查找会话对象
                FullTextSession txtSession = Search.getFullTextSession(sess);

                // 创建查询解析器:
                Analyzer analyzer;
                if (searchedEntity == null) {
                    analyzer = defaultAnalyzer;
                } else {
                    analyzer = txtSession.getSearchFactory().getAnalyzer(searchedEntity);
                }

                // search on all indexed fields: generate field list, removing internal hibernate search field name: _hibernate_class
                // 在索引的字段上进行查询：生成索引字段列表，删除内置的hibernate查询字段: _hibernate_class
                // 创建searchFactory准备进行查询
                SearchFactory searchFactory = txtSession.getSearchFactory();
                readerAccessor = searchFactory.getIndexReaderAccessor();
                reader = readerAccessor.open(searchedEntity);
                Collection<String> fieldNames = reader.getFieldNames(IndexReader.FieldOption.INDEXED);
                fieldNames.remove("_hibernate_class");
                String[] fnames = new String[0];
                fnames = fieldNames.toArray(fnames);

                // 在索引字段上执行查询
                String[] queries = new String[fnames.length];
                for (int i = 0; i < queries.length; ++i) {
                    queries[i] = searchTerm;
                }

                qry = MultiFieldQueryParser.parse(Version.LUCENE_35, queries, fnames, analyzer);
            } finally {
                if (readerAccessor != null && reader != null) {
                    readerAccessor.close(reader);
                }
            }
        }
        return qry;
    }

    /**
     * 为指定的类重新生成字段索引
     *
     * @param clazz 指定的类对象
     * @param sess hibernate session
     */
    public static void reindex(Class clazz, Session sess) {
        FullTextSession txtSession = Search.getFullTextSession(sess);
        MassIndexer massIndexer = txtSession.createIndexer(clazz);
        try {
            massIndexer.startAndWait();
        } catch (InterruptedException e) {
            log.error("mass reindexing interrupted: " + e.getMessage());
        } finally {
            txtSession.flushToIndexes();
        }
    }

    /**
     * 为所有的已索引对象重新生成索引
     *
     * @param async true 表示在后台异步生成索引
     * @param sess hibernate session
     */
    public static void reindexAll(boolean async, Session sess) {
        FullTextSession txtSession = Search.getFullTextSession(sess);
        MassIndexer massIndexer = txtSession.createIndexer();
        massIndexer.purgeAllOnStart(true);
        try {
            if (!async) {
                massIndexer.startAndWait();
            } else {
                massIndexer.start();
            }
        } catch (InterruptedException e) {
            log.error("mass reindexing interrupted: " + e.getMessage());
        } finally {
            txtSession.flushToIndexes();
        }
    }
}

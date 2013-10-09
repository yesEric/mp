package com.eric.dom;

import java.io.Serializable;

/**
 * ���е�ģ�Ͷ���Ӧ�ü̳�������࣬�����ڶ���Ĺ�����Ϣ��ʾ���Լ�����Ƚϵȡ�
 * @author Eric
 *
 */
public abstract class BaseObject implements Serializable {


	/**
	 * ����һ�����еģ����/֮�Ե��ַ����ڴ�ӡBean�ڵ���Ϣ
	 * 
	 * @return �����������ݵ��ַ�
	 */
	public abstract String toString();

	/**
	 * 
	 * ���ڶ���Ƚϣ���ʹ��Hibernateʱ����������Ӧ�����ڱȽϡ�
	 * @param o
	 *            Ҫ�ȽϵĶ���
	 * @return true/false ��ݷ��رȽϽ���booleanֵ 
	 */
	public abstract boolean equals(Object o);

	/**
	 * ���ʵ����equals���ж���Ƚϣ������Ҫʵ��hasCode����������ο�:
	 * http://www.hibernate.org/109.html
	 * 
	 * @return hashCode
	 */
	public abstract int hashCode();
	
	
}

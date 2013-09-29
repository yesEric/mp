package com.eric.dom;

import java.io.Serializable;

/**
 * 所有的模型对象都应该继承这个基类，它用于对象的公共信息显示，以及对象比较等。
 * @author Eric
 *
 */
public abstract class BaseObject implements Serializable {


	/**
	 * 返回一个多行的，包含键/之对的字符串，用于打印Bean内的信息
	 * 
	 * @return 描述对象内容的字符串。
	 */
	public abstract String toString();

	/**
	 * 
	 * 用于对象比较，当使用Hibernate时，对象主键应该用于比较。
	 * @param o
	 *            要比较的对象
	 * @return true/false 根据返回比较结果返回boolean值 
	 */
	public abstract boolean equals(Object o);

	/**
	 * 如果实现了equals进行对象比较，则必须要实现hasCode方法，具体参考:
	 * http://www.hibernate.org/109.html
	 * 
	 * @return hashCode
	 */
	public abstract int hashCode();
}

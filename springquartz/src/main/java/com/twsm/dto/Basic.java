package com.twsm.dto;

import java.io.Serializable;

/**
 * 所有对应数据库实体化Bean基类 DTO包中所有实体Bean都需要继承此基类
 * @author QiaoGuangqing
 * @date 2012-03-14
 * @description 定义表主键id 所有子类不需要重复定义
 *              重写toString方法 使得System.out有意义 方便调试
 */
public class Basic implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;			//任意表主键 继承此类后不需要在子类中再次定义ID 统一用此ID

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}

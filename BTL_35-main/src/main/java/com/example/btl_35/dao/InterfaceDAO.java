package com.example.btl_35.dao;

import java.util.List;

public interface InterfaceDAO<T> {

	public List<T> selectALl();

	public T selectById(int id);

	public void insert(T t);

	public void update(T t);

	public void delete(int id);

}

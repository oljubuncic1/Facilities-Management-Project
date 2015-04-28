package com.oljubuncic1.models;


import java.io.Serializable;
import java.util.Collection;



public interface ICrud<T, PK extends Serializable>
{
	
	public PK create(T t);
    public T read(PK id);
    public void update(T t);
    public void delete(PK id);
    public Collection<T> getAll();

}

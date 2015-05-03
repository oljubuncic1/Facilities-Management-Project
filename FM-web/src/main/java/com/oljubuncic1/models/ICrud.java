package com.oljubuncic1.models;


import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;



public interface ICrud<T, PK extends Serializable>
{
	@Transactional(readOnly = false)
	public PK create(T t);
    public T read(PK id);
    public void update(T t);
    public void delete(PK id);
    @Transactional
    public Collection<T> getAll();

}

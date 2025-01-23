package model.mapper;

import model.Produs;

import java.util.List;

public interface DataMapper<T> {
    public List<T> readAll();
    public void save(List<T> obiecte);
}

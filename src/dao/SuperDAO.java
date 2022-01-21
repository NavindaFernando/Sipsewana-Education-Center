package dao;

import entity.SuperEntity;

import java.util.List;

public interface SuperDAO <Entity extends SuperEntity, ID>{
    public boolean add(Entity entity) throws Exception;

    public boolean update(Entity entity) throws Exception;

    public boolean delete(ID id) throws Exception;

    public Entity find(ID id) throws Exception;

    public List<Entity> findAll() throws Exception;
}

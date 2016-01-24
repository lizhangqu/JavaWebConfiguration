package cn.edu.zafu.dao;

/**
 * Created by lizhangqu on 16/1/24.
 */

import org.mybatis.spring.SqlSessionTemplate;

import java.util.*;


public abstract class BaseDAO<T> {
    SqlSessionTemplate sqlSessionTemplate;

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public boolean insert(T t) {
        int i = getSession().insert(getStatement("insert"), t);
        return i > 0;
    }

    public boolean update(T t) {
        int i = getSession().update(getStatement("update"), t);
        return i > 0;
    }

    public Object querySimpleResult(String statement, Object... query) {
        FutureResultHandler handler = new FutureResultHandler();
        getSession().select(getStatement(statement), transform(query), handler);
        return handler.get();
    }

    public T findById(Integer id) {
        return getSession().selectOne(getStatement("findById"), id);
    }

    public T findById(String id) {
        return getSession().selectOne(getStatement("findById"), id);
    }

    public T findById(Object map) {
        return getSession().selectOne(getStatement("findById"), map);
    }

    public T findOne(String statement, Object... query) {
        return getSession().selectOne(getStatement(statement), transform(query));
    }

    public List<T> findAll() {
        return getSession().selectList(getStatement("findAll"));
    }

    public List<T> query(Object... query) {
        return queryStatement("query", query);
    }

    public PaginationResult<T> queryPage(int pageNumber, int pageSize, Object... query) {
        PaginationResult<T> result = new PaginationResult<T>(pageNumber, pageSize);

        int total = (Integer) querySimpleResult("count", query);
        result.setTotal(total);

        Map<Object, Object> map = new HashMap<Object, Object>();
        if (query.length > 1) {
            map.putAll(listToMap(Arrays.asList(query)));
        } else if (query.length == 1 && query[0] instanceof Map) {
            map.putAll((Map) query[0]);
        }
        map.put("offset", result.getOffset());
        map.put("rows", result.getPageSize());
        List<T> list = getSession().selectList(getStatement("pagination"), map);
        result.setList(list);
        return result;
    }

    private Map<Object, Object> listToMap(List<Object> params) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        if (params.size() > 0 && params.size() % 2 == 0) {
            Iterator<Object> it = params.iterator();
            while (it.hasNext()) {
                map.put(it.next(), it.next());
            }
        }
        return map;
    }

    public List<T> queryStatement(String statement, Object... query) {
        return getSession().selectList(getStatement(statement), transform(query));
    }

    private Object transform(Object... query) {
        Object object;
        if (query.length > 1) {
            object = listToMap(Arrays.asList(query));
        } else if (query.length == 1) {
            object = query[0];
        } else {
            object = null;
        }
        return object;
    }

    protected SqlSessionTemplate getSession() {
        return sqlSessionTemplate;
    }

    public boolean deleteById(int id) {
        int i = getSession().delete(getStatement("deleteById"), id);
        return i > 0;
    }

    public boolean deleteById(String id) {
        int i = getSession().delete(getStatement("deleteById"), id);
        return i > 0;
    }

    protected String getStatement(String name) {
        return getClass().getName() + "." + name;
    }
}

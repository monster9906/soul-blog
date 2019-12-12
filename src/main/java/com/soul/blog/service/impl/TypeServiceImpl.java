package com.soul.blog.service.impl;

import com.soul.blog.dao.TypeDao;
import com.soul.blog.domain.Type;
import com.soul.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author 符浩灵
 * @date 2019/11/21 21:38
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        Type save = typeDao.save(type);
        return save;
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getOne(id);
    }
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeDao.getOne(id);
        if(t.equals("") || t == null){
            throw  new RuntimeException("该类别不存在");
        }
        BeanUtils.copyProperties(type,t );
        return typeDao.save(t);
    }

    @Transactional
    @Override
    public void deleteType(long id) {
        typeDao.deleteById(id);
    }

    @Override
    public Type findByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        PageRequest pageable = PageRequest.of(0, size, Sort.Direction.DESC, "blogs.size");
        return typeDao.findTop(pageable);
    }
}

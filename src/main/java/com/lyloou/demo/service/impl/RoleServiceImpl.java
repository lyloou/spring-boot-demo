package com.lyloou.demo.service.impl;

import com.lyloou.demo.dao.RoleDao;
import com.lyloou.demo.pojo.PageData;
import com.lyloou.demo.pojo.Role;
import com.lyloou.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao = null;

    @Override
    @Cacheable(key = "'role_' + #id", cacheNames = "redis-cache")
    public Role getRole(Long id) {
        return roleDao.getRole(id);
    }

    @Override
    public PageData<Role> findRoles(String roleName, int pageNum, int pageSize) {

        int start = (pageNum - 1) * pageSize;

        List<Role> roleList = roleDao.findRoles(roleName, start, pageSize);

        int total = roleDao.countRoles(roleName);

        PageData<Role> pageRoles = new PageData<>(total, pageSize, pageNum, roleList);
        return pageRoles;
    }

    @Value("${spring.cache.cache-names}")
    private String prefix = null;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate = null;

    @Override
    public int insertRole(Role role) {
        int result = roleDao.insertRole(role);
        if (result > 0) {
            String key = prefix + "::role_" + role.getId();
            redisTemplate.opsForValue().set(key, role);
        }
        return result;
    }

    @Override
    public int updateRole(Role role) {
        int result = this.roleDao.updateRole(role); // 而不是从redis缓存中获取（因为缓存中有可能是脏数据）
        if (result > 0) {
            String key = prefix + "::role_" + role.getId();
            redisTemplate.opsForValue().set(key, role);
        }
        return result;
    }

    /**
     * 这个方法标注了注解 @CachePut，意思为将结果保存到 Redis 缓存中，
     * 而在其 key 配置项中的 EL 配置了#result.id，其中 #result 表示返回结果，
     * 而 id 表示其属性，因为在插入数据库的过程中，id 是通过数据库机制生成返回的，所以只能那样获取了。
     * 在 updateRole 和 insertRole 方法中，不便使用缓存注解，因此这里我采用了编程的方法进行处理。（如上方的 updateRole(Role role) 方法）
     * 如果执意要做，结果会缓存的是：redis-cache::role_null
     *
     * @param role
     * @return
     */
    @CachePut(key = "'role_' + #result.id", cacheNames = "redis-cache")
    public Role insertRole2(Role role) {
        int result = roleDao.insertRole(role);
        return role;
    }

    @Override
    @CacheEvict(key = "'role_' + #result.id", cacheNames = "redis-cache")
    public Role deleteRole(Long id) {
        Role role = this.roleDao.getRole(id);
        int result = this.roleDao.deleteRole(id);
        return result > 0 ? role : null;
    }
}
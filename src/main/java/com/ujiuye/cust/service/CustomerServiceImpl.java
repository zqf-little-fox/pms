package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.cust.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public void saveInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        CustomerExample example = new CustomerExample();
        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }

    @Override
    public Customer getCustomerDetail(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idList = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idList);
        int row = customerMapper.deleteByExample(example);
        return ids.length == row;
    }

    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if (cid == 0){
            criteria.andComnameLike("%" + keyword + "%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%" + keyword + "%");
            example.or(criteria1);
        }else if (cid == 1){
            criteria.andComnameLike("%" + keyword + "%");
        }else{
            criteria.andCompanypersonLike("%" + keyword + "%");
        }
        if (orderby == 1){
            example.setOrderByClause("id desc");
        }
        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }
}

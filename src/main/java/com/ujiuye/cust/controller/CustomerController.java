package com.ujiuye.cust.controller;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cust")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        Workbook wb = new HSSFWorkbook();
        //在表格里创建sheet对象
        Sheet sheet = wb.createSheet("customers");
        sheet.setColumnWidth(3,4000);
        Row row = sheet.createRow(0);
        Cell[] cells = new HSSFCell[5];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = row.createCell(i);
        }
        cells[0].setCellValue("ID");
        cells[1].setCellValue("联系人");
        cells[2].setCellValue("公司名称");
        cells[3].setCellValue("添加时间");
        cells[4].setCellValue("联系电话");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Customer> list = customerService.getCustomerList();
        for (int i = 0; i < list.size(); i++) {
            Customer customer = list.get(i);

            Row row1 = sheet.createRow(i + 1);
            Cell[] cell = new HSSFCell[5];
            for (int j = 0; j < cell.length; j++) {
                cell[j] = row1.createCell(j);
            }
            cell[0].setCellValue(customer.getId());
            cell[1].setCellValue(customer.getCompanyperson());
            cell[2].setCellValue(customer.getComname());
            String addtime = sdf.format(customer.getAddtime());
            cell[3].setCellValue(addtime);
            cell[4].setCellValue(customer.getComphone());
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("d:\\Desktop\\customers.xls"));
            wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","下载成功");
        return map;
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer id){
        return customerService.getCustomerDetail(id);
    }

    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCuetomerJsonList(){
        return customerService.getCustomerList();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(Integer cid,String keyword, Integer orderby){
        List<Customer> list = customerService.search(cid, keyword, orderby);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = customerService.batchDelete(ids);
        Map<String, Object> map = new HashMap<>();
        if (result  == true){
            map.put("statusCode", 200);
            map.put("message", "删除成功");
        }else{
            map.put("statusCode", 500);
            map.put("message", "删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.updateCustomer(customer);
        return "redirect:/cust/list";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer id, Map<String, Object> map){
        Customer customer = customerService.getCustomerDetail(id);
        map.put("customer",customer);
        return "customer-edit";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Map<String, Object> map){
        Customer customer = customerService.getCustomerDetail(id);
        map.put("customer",customer);
        return "customer-look";
    }

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customerService.saveInfo(customer);
        return "redirect:/cust/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getCuetomerList(){
        List<Customer> list = customerService.getCustomerList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;
    }
}

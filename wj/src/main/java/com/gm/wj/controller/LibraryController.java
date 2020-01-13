package com.gm.wj.controller;

import com.gm.wj.entity.Book;
import com.gm.wj.entity.Category;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultCode;
import com.gm.wj.result.ResultFactory;
import com.gm.wj.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Api(description = "图书管理")
@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "列表")
    @PostMapping("/books")
    public Result list(@RequestBody Book book) throws Exception {
        return ResultFactory.buildResult(ResultCode.SUCCESS,"操作成功",bookService.bookList(book));
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/saveBooks")
    public Result addOrUpdate(@RequestBody Book book) throws Exception {
        Integer result = bookService.saveBook(book);
        if(result > 0){
            return ResultFactory.buildResult(ResultCode.SUCCESS,"操作成功","");
        }
        return ResultFactory.buildResult(ResultCode.FAIL,"操作失败","");

    }

    @ApiOperation(value = "详情")
    @PostMapping("/{id}/detail")
    public Result detail(@PathVariable("id") Integer id) throws Exception {
        Book book = bookService.detail(id);
        book.setCategory(new Category());
        book.getCategory().setId(book.getCid());
        if (book == null){
            return ResultFactory.buildResult(ResultCode.FAIL,"详情获取失败","");
        }else{
            return ResultFactory.buildResult(ResultCode.SUCCESS,"获取详情成功",book);
        }
    }

    @ApiOperation(value = "删除")
    @PostMapping("/deleteByIds/{ids}")
    public Result deleteByIds(@PathVariable("ids")List<Integer> ids) throws Exception {
        Integer deleteCount = bookService.deleteBook(ids);
        if(deleteCount > 0){
            return ResultFactory.buildResult(ResultCode.SUCCESS,"删除成功,删除了"+deleteCount+"条数据",deleteCount);
        }
        return ResultFactory.buildResult(ResultCode.FAIL,"删除失败",0);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable("id")Integer id) throws Exception {
        List ids = new ArrayList();
        ids.add(id);
        Integer deleteCount = bookService.deleteBook(ids);
        if(deleteCount > 0){
            return ResultFactory.buildResult(ResultCode.SUCCESS,"删除成功,删除了"+deleteCount+"条数据",deleteCount);
        }
        return ResultFactory.buildResult(ResultCode.FAIL,"删除失败",0);
    }

    @ApiOperation(value = "检索")
    @PostMapping("/{keywords}/search")
    public Result searchResult(@PathVariable("keywords") String keywords) throws Exception {
        return ResultFactory.buildResult(ResultCode.SUCCESS,"OK",keywords);
    }


    @ApiOperation(value = "图书分类列表")
    @GetMapping("/categories/cateList")
    public Result cateList() throws Exception {
        List<Category> categoryList = new LinkedList<>();
        Category category = new Category();
        category.setId(1);
        category.setName("文学");
        categoryList.add(category);
        category = new Category();
        category.setId(2);
        category.setName("流行");
        categoryList.add(category);
        category = new Category();
        category.setId(3);
        category.setName("文化");

        categoryList.add(category);
        category = new Category();
        category.setId(4);
        category.setName("生活");

        categoryList.add(category);
        category = new Category();
        category.setId(5);
        category.setName("经管");

        categoryList.add(category);
        category = new Category();
        category.setId(6);
        category.setName("科技");
        categoryList.add(category);
        return ResultFactory.buildResult(ResultCode.SUCCESS,"ok",categoryList);
    }



}

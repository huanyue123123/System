package com.gm.wj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "图书管理")
@RestController
public class LibraryController {

    @ApiOperation(value = "列表")
    @GetMapping("/api/books")
    public List<String> list() throws Exception {
        return null;
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/api/books")
    public String addOrUpdate(@RequestBody String book) throws Exception {
        return null;
    }

    @ApiOperation(value = "删除")
    @PostMapping("/api/delete")
    public void delete(@RequestBody String book) throws Exception {
    }

    @ApiOperation(value = "检索")
    @PostMapping("/api/search")
    public List<String> searchResult(@RequestBody String s) throws Exception {
        return null;
    }

    @ApiOperation(value = "按照分类查询书列表")
    @GetMapping("/api/categories/{cid}/books")
    public List<String> listByCategory(@PathVariable("cid") int cid) throws Exception {
        return null;
    }

    @ApiOperation(value = "上传")
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file, HttpServletRequest request) throws Exception {
        return null;
    }


}

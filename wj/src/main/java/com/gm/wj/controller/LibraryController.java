package com.gm.wj.controller;

import com.gm.wj.entity.Book;
import com.gm.wj.entity.Category;
import com.gm.wj.result.Result;
import com.gm.wj.result.ResultCode;
import com.gm.wj.result.ResultFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Api(description = "图书管理")
@RestController
public class LibraryController {

    @ApiOperation(value = "列表")
    @GetMapping("/api/books")
    public Result list() throws Exception {
        return null;
    }

    @ApiOperation(value = "添加或修改")
    @PostMapping("/api/saveBooks")
    public Result addOrUpdate(@RequestBody Book book) throws Exception {
        return ResultFactory.buildResult(ResultCode.SUCCESS,"OK",book);
    }

    @ApiOperation(value = "删除")
    @PostMapping("/api/delete")
    public void delete(@RequestBody String book) throws Exception {
    }

    @ApiOperation(value = "检索")
    @PostMapping("/api/{keywords}/search")
    public Result searchResult(@PathVariable("keywords") String keywords) throws Exception {
        return ResultFactory.buildResult(ResultCode.SUCCESS,"OK",keywords);
    }

    @ApiOperation(value = "按照分类查询书列表")
    @GetMapping("/api/categories/{cid}/books")
    public Result listByCategory(@PathVariable("cid") int cid) throws Exception {

        return ResultFactory.buildResult(ResultCode.SUCCESS,"ok",cid);
    }

    @ApiOperation(value = "图书分类列表")
    @GetMapping("/api/categories/cateList")
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

    /**
     * base64EncoderImg为最终图片转换为base64
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传")
    @PostMapping("api/covers")
    public Result coversUpload(MultipartFile file, HttpServletRequest request) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(file != null){
            BASE64Encoder base64Encoder =new BASE64Encoder();
            String fileName = file.getOriginalFilename();
            String lastName = fileName.substring(fileName.lastIndexOf(".")+1);
            String base64EncoderImg = "data:image/"+lastName+";base64,"+ base64Encoder.encode(file.getBytes());
            map.put("base64",base64EncoderImg);
            return ResultFactory.buildResult(ResultCode.SUCCESS,"OK",map);
        }
        return ResultFactory.buildResult(ResultCode.FAIL,"文件上传失败,未接收到文件",null);
    }


}

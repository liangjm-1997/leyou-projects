package com.leyou.item.controller;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    @GetMapping(value = "/list")
    @ApiOperation(value = "获取category", notes = "通过PID获取category信息")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam("pid") Long pid ){
        if ( null == pid || pid.longValue() <0 ){
            // 响应400，相当于ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            return ResponseEntity.badRequest().build();
        }
        List<Category> categoryList = categoryService.qryCategory(pid);
        if (CollectionUtils.isEmpty(categoryList)) {
            // 响应404
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryList);
    }
}

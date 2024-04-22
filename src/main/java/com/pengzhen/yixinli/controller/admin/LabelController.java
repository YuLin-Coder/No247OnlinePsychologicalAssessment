package com.pengzhen.yixinli.controller.admin;

import com.pengzhen.yixinli.entity.Label;
import com.pengzhen.yixinli.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章标签
 */
@RestController
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping("/admin/label/list")
    public List<Label> list() {
        List<Label> labels = labelService.selectAll();
        return labels;
    }


}

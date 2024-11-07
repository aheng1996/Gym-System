package com.milotnt.controller;

import com.milotnt.pojo.Equipment;
import com.milotnt.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author MiloTnT
 */
@RestController
@RequestMapping("/api/equipment") // 修改路径前缀为 /api
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    // 查询器材
    @GetMapping
    public List<Equipment> selectEquipment() {
        return equipmentService.findAll();
    }

    // 删除器材
    @DeleteMapping("/{equipmentId}")
    public void deleteEquipment(@PathVariable Integer equipmentId) {
        equipmentService.deleteByEquipmentId(equipmentId);
    }

    // 修改器材
    @PutMapping
    public Equipment updateEquipment(@RequestBody Equipment equipment) {
        equipmentService.updateEquipmentByEquipmentId(equipment);
        return equipment;
    }

    // 新增器材
    @PostMapping
    public Equipment addEquipment(@RequestBody Equipment equipment) {
        equipmentService.insertEquipment(equipment);
        return equipment;
    }

    // 根据器材ID查询
    @GetMapping("/{equipmentId}")
    public List<Equipment> selectByEquipmentId(@PathVariable Integer equipmentId) {
        return equipmentService.selectByEquipmentId(equipmentId);
    }
}

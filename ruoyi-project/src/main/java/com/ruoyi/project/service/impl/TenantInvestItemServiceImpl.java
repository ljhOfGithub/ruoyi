package com.ruoyi.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.project.entity.TenantInvestItem;
import com.ruoyi.project.dto.CreateTabDTO;
import com.ruoyi.project.mapper.TenantInvestItemMapper;
import com.ruoyi.project.service.ITenantInvestItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @description tenant_invest_item
 * @author lijihao
 * @date 2024-07-09
 */
@RequiredArgsConstructor
@Service
public class TenantInvestItemServiceImpl extends ServiceImpl<TenantInvestItemMapper, TenantInvestItem> implements ITenantInvestItemService {
    private final JdbcTemplate jdbcTemplate;
    private void createVarTable(Long spaceId, Long manifestId) {
        List<CreateTabDTO.DbColumn> columns = new ArrayList<>();
//        List<VarProcessVariableDTO> varDTOList = varProcessManifestVariableService.getVariableInfosByManifestId(spaceId, manifestId);
//        for (VarProcessVariableDTO var : varDTOList) {
//            columns.add(CreateTabDTO.DbColumn.builder().columnName(var.getName()).columnDataType(var.getDataType()).isIndex(var.getIsIndex()).colRole(var.getColRole()).build());
//        }
//        if (columns.isEmpty()) {
//            throw new VariableMgtBusinessServiceException(VariableMgtErrorCode.MANIFEST_CREATE_TABLE_FAIL, "变量清单里无任何变量，请添加.");
//        }
        String tableName = String.format("var_process_manifest_%s", manifestId);
        CreateTabDTO createTabDTO = new CreateTabDTO();
        createTabDTO.setTableName(tableName);
        createTabDTO.setColumns(columns);
//        APIResult<String> feignResult = varProcessConsumerFeign.createVarTable(createTabDTO);
//        if (!Objects.equals(feignResult.getCode(), STRING_0)) {
//            throw new VariableMgtBusinessServiceException(VariableMgtErrorCode.MANIFEST_CREATE_TABLE_FAIL, feignResult.getMessage());
//        }
    }
}
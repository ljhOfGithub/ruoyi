package com.ruoyi.project.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
//@Schema(description = "变量清单状态变更输入参数")
public class TenantInvestItemMutationInputDto implements Serializable {

    private static final long serialVersionUID = 7786278302679481035L;

//    @Schema(description = "变量清单 ID", required = true)
    @NotNull(message = "投资明细表格 ID 不能为空")
    private Long investTableId;

}
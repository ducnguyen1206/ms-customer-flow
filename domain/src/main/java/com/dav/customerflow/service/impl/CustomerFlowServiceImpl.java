package com.dav.customerflow.service.impl;

import com.dav.customerflow.data.CustomerFlowData;
import com.dav.customerflow.dto.ProductDto;
import com.dav.customerflow.entity.Branch;
import com.dav.customerflow.entity.Category;
import com.dav.customerflow.entity.Product;
import com.dav.customerflow.entity.ProductBranch;
import com.dav.customerflow.enumf.StatusEnum;
import com.dav.customerflow.mapper.EntityMapper;
import com.dav.customerflow.service.CustomerFlowService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static com.dav.customerflow.utils.CommonUtils.toJsonString;

@AllArgsConstructor
@Log4j2
public class CustomerFlowServiceImpl implements CustomerFlowService {

    @Autowired
    private final CustomerFlowData customerFlowData;

    @Override
    public void save(ProductDto productRequest, String createdBy) {
        log.info("Saving product details");

        // Get categoryId
        Category category =  customerFlowData.getCategoryByCategoryName(productRequest.getCategoryName());
        if (Objects.isNull(category)) {
            log.error("Failed to get category");
            //TODO handle exception here 404
            return;
        }

        log.info("Category returned {}", toJsonString(category));

        // Get branchId
        Branch branch = customerFlowData.getBranchByBranchCode(productRequest.getBranchCode());
        if (Objects.isNull(branch)) {
            log.error("Failed to get branch");
            //TODO handle exception here 404
            return;
        }

        log.info("Branch returned {}", toJsonString(branch));

        Long categoryId = category.getId();
        Long branchId = branch.getId();

        // Validating product name
        if (customerFlowData.existsByProductName(productRequest.getProductName())) {
            log.error("Product name already existed");
            // TODO handle exception 409
            return;
        }

        Product product = EntityMapper.INSTANCE.productDtoToProduct(productRequest);
        product.setCategoryId(categoryId);

        product = customerFlowData.save(product);
        log.info("Saved product {}", toJsonString(product));

        ProductBranch productBranch = ProductBranch.builder()
                .productId(product.getId())
                .branchId(branchId)
                .status(StatusEnum.ACTIVE.name())
                .build();
        log.info("Saving new product branch {}", toJsonString(productBranch));

        customerFlowData.saveProductBranch(productBranch);
    }
}

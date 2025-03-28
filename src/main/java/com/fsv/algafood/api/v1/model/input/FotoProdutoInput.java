package com.fsv.algafood.api.v1.model.input;

import com.fsv.algafood.core.validation.FileContentType;
import com.fsv.algafood.core.validation.FileSize;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class FotoProdutoInput {

    @NotNull
    @FileSize(max = "500KB")
    @ApiModelProperty(hidden = true)
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
    private MultipartFile arquivo;

    @NotBlank
    @ApiModelProperty(value = "Descrição da foto do produto", required = true)
    private String descricao;
}

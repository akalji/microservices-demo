package com.akalji.learn.microservices.resourceservice.common.dto;

import com.akalji.learn.microservices.commons.domain.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nikolai_Tikhonov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto implements Dto {
    private Integer id;
}

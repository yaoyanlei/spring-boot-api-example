package com.apelab.example.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yaoyanlei on 2020/5/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleDomain extends BaseDomain<Long>{

	private String name;

}

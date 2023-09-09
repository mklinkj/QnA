package org.mklinkj.library.pagination.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageRequest {

  private int pageNumber;

  private int pageSize;
}

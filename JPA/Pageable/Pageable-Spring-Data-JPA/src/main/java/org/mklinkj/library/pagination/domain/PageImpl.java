package org.mklinkj.library.pagination.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageImpl<T> {
  private PageRequest pageRequest;

  private long totalElements;

  private int totalPages;

  private T content;
}

package org.mklinkj.library.pagination.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

  @Builder.Default private int page = 1;

  @Builder.Default private int size = PageConstants.PAGE_SIZE;

  public int getOffset() {
    return (page - 1) * size;
  }
}

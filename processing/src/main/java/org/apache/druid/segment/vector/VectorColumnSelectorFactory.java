/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.segment.vector;

import org.apache.druid.query.dimension.DimensionSpec;
import org.apache.druid.segment.column.ColumnCapabilities;

import javax.annotation.Nullable;

/**
 *
 *
 * @see org.apache.druid.segment.ColumnSelectorFactory, the non-vectorized version.
 */
public interface VectorColumnSelectorFactory
{
  /**
   * Returns the maximum vector size for the {@link VectorCursor} that generated this object.
   *
   * @see VectorCursor#getMaxVectorSize()
   */
  int getMaxVectorSize();

  /**
   * Returns a string-typed, single-value-per-row column selector.
   */
  SingleValueDimensionVectorSelector makeSingleValueDimensionSelector(DimensionSpec dimensionSpec);

  /**
   * Returns a string-typed, multi-value-per-row column selector.
   */
  MultiValueDimensionVectorSelector makeMultiValueDimensionSelector(DimensionSpec dimensionSpec);

  /**
   * Returns a primitive column selector.
   */
  VectorValueSelector makeValueSelector(String column);

  /**
   * Returns an object selector, useful for complex columns.
   */
  VectorObjectSelector makeObjectSelector(String column);

  /**
   * Returns capabilities of a particular column, or null if the column doesn't exist. Unlike ColumnSelectorFactory,
   * null does not potentially indicate a dynamically discovered column.
   *
   * @return capabilities, or null if the column doesn't exist.
   */
  @Nullable
  ColumnCapabilities getColumnCapabilities(String column);
}

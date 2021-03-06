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
package hivemall.xgboost.classification;

import hivemall.xgboost.XGBoostUDTF;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.metadata.HiveException;

/**
 * A XGBoost binary classification and the document is as follows; -
 * https://github.com/dmlc/xgboost/tree/master/demo/binary_classification
 */
@Description(name = "train_xgboost_classifier",
        value = "_FUNC_(string[] features, double target [, string options]) - Returns a relation consisting of <string model_id, array<byte> pred_model>")
public final class XGBoostBinaryClassifierUDTF extends XGBoostUDTF {

    public XGBoostBinaryClassifierUDTF() {
        super();
    }

    {
        // Settings for binary classification
        params.put("objective", "binary:logistic");
        params.put("eval_metric", "error");
    }

    @Override
    protected void checkTargetValue(final double target) throws HiveException {
        if (!(Double.compare(target, 0.0) == 0 || Double.compare(target, 1.0) == 0)) {
            throw new HiveException("target must be 0.0 or 1.0: " + target);
        }
    }

}

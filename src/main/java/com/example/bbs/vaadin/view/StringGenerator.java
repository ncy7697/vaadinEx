/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.bbs.vaadin.view;

import com.vaadin.shared.util.SharedUtil;

public class StringGenerator {
    static String[] strings = new String[] { "lorem", "ipsum", "dolor", "sit",
            "amet", "consectetur", "quid", "securi", "etiam", "tamquam", "eu",
            "fugiat", "nulla", "pariatur" };
    int stringCount = -1;

    public String nextString(boolean capitalize) {
        if (++stringCount >= strings.length) {
            stringCount = 0;
        }
        return capitalize ? SharedUtil.capitalize(strings[stringCount])
                : strings[stringCount];
    }

}

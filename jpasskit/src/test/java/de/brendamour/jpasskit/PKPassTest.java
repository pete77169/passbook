/**
 * Copyright (C) 2012 Patrice Brend'amour <p.brendamour@bitzeche.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.brendamour.jpasskit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.brendamour.jpasskit.passes.PKGenericPass;

public class PKPassTest {
    private static final String COLOR_STRING = "rgb(1,2,3)";
    private static final Color COLOR_OBJECT = new Color(1, 2, 3);
    private PKPass pkPass;

    @Before
    public void prepareTest() {
        pkPass = new PKPass();
    }

    @Test
    public void test_colorConversionFromString() {

        pkPass.setBackgroundColor(COLOR_STRING);

        Assert.assertEquals(pkPass.getBackgroundColor(), COLOR_STRING);
        Assert.assertEquals(pkPass.getBackgroundColorAsObject(), COLOR_OBJECT);

    }

    @Test
    public void test_colorConversionFromObject() {

        pkPass.setBackgroundColorAsObject(COLOR_OBJECT);

        Assert.assertEquals(pkPass.getBackgroundColor(), COLOR_STRING);
        Assert.assertEquals(pkPass.getBackgroundColorAsObject(), COLOR_OBJECT);

    }

    @Test
    public void test_includesPassErrors() {
        PKGenericPass subPass = mock(PKGenericPass.class);
        List<String> subArrayListWithErrors = new ArrayList<String>();
        String someValidationMessage = "Some error";
        subArrayListWithErrors.add(someValidationMessage);

        pkPass.setGeneric(subPass);

        when(subPass.isValid()).thenReturn(false);
        when(subPass.getValidationErrors()).thenReturn(subArrayListWithErrors);

        List<String> validationErrors = pkPass.getValidationErrors();

        Assert.assertTrue(validationErrors.size() > 0);
        Assert.assertTrue(validationErrors.contains(someValidationMessage));

    }
}

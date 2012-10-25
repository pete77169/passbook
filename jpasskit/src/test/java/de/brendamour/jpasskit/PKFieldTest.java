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

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.brendamour.jpasskit.enums.PKDateStyle;
import de.brendamour.jpasskit.enums.PKNumberStyle;
import de.brendamour.jpasskit.enums.PKTextAlignment;

public class PKFieldTest {
    private static final String KEY = "key";
    private static final String VALUE_TEXT = "some Text";
    private static final String CHANGEMESSAGE = "Changed %@";
    private static final String LABEL = "Label";
    private static final BigDecimal VALUE_CURRENCY = new BigDecimal(25.20).setScale(2, RoundingMode.HALF_UP);
    private static final String CURRENCYCODE = "EUR";
    private PKField pkField;

    @Before
    public void prepareTest() {
        pkField = new PKField();
    }

    @Test
    public void test_GetterSetter_Text() {
        fillFieldsText();

        Assert.assertEquals(pkField.getKey(), KEY);
        Assert.assertEquals(pkField.getValue(), VALUE_TEXT);
        Assert.assertEquals(pkField.getChangeMessage(), CHANGEMESSAGE);
        Assert.assertEquals(pkField.getLabel(), LABEL);
        Assert.assertTrue(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_NoKey() {
        fillFieldsText();
        pkField.setKey(null);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_EmptyKey() {
        fillFieldsText();
        pkField.setKey("");

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_NoValue() {
        fillFieldsText();
        pkField.setValue(null);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_InvalidValueType() {
        fillFieldsText();
        pkField.setValue(new PKField());

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_Currency() {
        fillFieldsCurrency();

        Assert.assertEquals(pkField.getValue(), VALUE_CURRENCY);
        Assert.assertTrue(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_CurrencyAndNumberFormatSet() {
        fillFieldsCurrency();
        pkField.setNumberStyle(PKNumberStyle.PKNumberStyleDecimal);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_CurrencyAndDateStyleSet() {
        fillFieldsCurrency();
        pkField.setDateStyle(PKDateStyle.PKDateStyleFull);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_CurrencyAndTimeStyleSet() {
        fillFieldsCurrency();
        pkField.setTimeStyle(PKDateStyle.PKDateStyleFull);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_NumberAndDateStyleSet() {
        fillBasisFields();

        pkField.setNumberStyle(PKNumberStyle.PKNumberStyleDecimal);
        pkField.setDateStyle(PKDateStyle.PKDateStyleFull);

        Assert.assertFalse(pkField.isValid());

    }

    @Test
    public void test_GetterSetter_ChangeMessageWithNoPlaceholder() {
        fillBasisFields();

        pkField.setChangeMessage("Change");

        Assert.assertFalse(pkField.isValid());

    }

    private void fillFieldsText() {
        pkField.setValue(VALUE_TEXT);
        fillBasisFields();
    }

    private void fillBasisFields() {
        pkField.setKey(KEY);
        pkField.setChangeMessage(CHANGEMESSAGE);
        pkField.setLabel(LABEL);
        pkField.setTextAlignment(PKTextAlignment.PKTextAlignmentCenter);
    }

    private void fillFieldsCurrency() {
        pkField.setValue(VALUE_CURRENCY);
        pkField.setCurrencyCode(CURRENCYCODE);
        fillBasisFields();
    }
}

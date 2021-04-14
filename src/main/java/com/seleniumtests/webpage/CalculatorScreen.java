/*
 * Copyright 2015 www.seleniumtests.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seleniumtests.webpage;

import org.openqa.selenium.By;

import com.seleniumtests.core.SeleniumTestsContextManager;
import com.seleniumtests.webelements.ButtonElement;
import com.seleniumtests.webelements.LabelElement;
import com.seleniumtests.webelements.PageObject;

/**
 * Defines service for Calculator screen.
 */
public class CalculatorScreen extends PageObject {

    public CalculatorScreen() throws Exception {
    }

    /**
     * Opens log in page.
     *
     * @param openAPP
     * @throws Exception
     */
    public CalculatorScreen(final boolean openAPP) throws Exception {
        super(null, openAPP ? SeleniumTestsContextManager.getThreadContext().getApp() : null);
    }

    public ButtonElement getDigitElement(final String symbol) {
        // resource-id in appium inspector is the id
        return new ButtonElement("Digit Button", By.id(String.format("com.android.calculator2:id/digit_%s", symbol)));
    }

    public ButtonElement getEqualElement() {
        return new ButtonElement("Equal Button", By.id("com.android.calculator2:id/eq"));
    }

    public ButtonElement getPlusElement() {
        return new ButtonElement("Equal Button", By.id("com.android.calculator2:id/op_add"));
    }

    public CalculatorScreen clickDigit(final String symbol) {
        getDigitElement(symbol).click();
        return this;
    }

    public CalculatorScreen clickPlus() {
        getPlusElement().click();
        return this;
    }

    public CalculatorScreen clickEqual() {
        getEqualElement().click();
        return this;
    }

    public String getResultText() {
        return new LabelElement("Result Text", By.id("com.android.calculator2:id/formula")).getText();
    }
}

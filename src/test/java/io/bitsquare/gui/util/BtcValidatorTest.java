/*
 * This file is part of Bitsquare.
 *
 * Bitsquare is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bitsquare is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bitsquare. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bitsquare.gui.util;

import io.bitsquare.gui.util.validation.BtcValidator;

import com.google.bitcoin.core.Coin;
import com.google.bitcoin.core.NetworkParameters;

import org.junit.Test;

import static org.junit.Assert.*;

public class BtcValidatorTest {
    @Test
    public void testIsValid() {
        BtcValidator validator = new BtcValidator();

        assertTrue(validator.validate("1").isValid);
        assertTrue(validator.validate("1,1").isValid);
        assertTrue(validator.validate("1.1").isValid);
        assertTrue(validator.validate(",1").isValid);
        assertTrue(validator.validate(".1").isValid);
        assertTrue(validator.validate("0.12345678").isValid);
        assertTrue(validator.validate(Coin.SATOSHI.toPlainString()).isValid);
        assertTrue(validator.validate(NetworkParameters.MAX_MONEY.toPlainString()).isValid);

        assertFalse(validator.validate(null).isValid);
        assertFalse(validator.validate("").isValid);
        assertFalse(validator.validate("0").isValid);
        assertFalse(validator.validate("0.0").isValid);
        assertFalse(validator.validate("0,1,1").isValid);
        assertFalse(validator.validate("0.1.1").isValid);
        assertFalse(validator.validate("1,000.1").isValid);
        assertFalse(validator.validate("1.000,1").isValid);
        assertFalse(validator.validate("0.123456789").isValid);
        assertFalse(validator.validate("-1").isValid);
        assertFalse(validator.validate(String.valueOf(NetworkParameters.MAX_MONEY.longValue() + Coin.SATOSHI
                .longValue())).isValid);
    }

}

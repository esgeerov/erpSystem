package com.samir.erpSystem.myenum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum EnumAviable {
    ACTIVE(1), DEACTIVE(0);
    public int value;
}

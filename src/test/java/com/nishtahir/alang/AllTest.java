package com.nishtahir.alang;

import com.nishtahir.alang.utils.StringUtilsTest;
import com.nishtahir.alang.value.IntegerValueTest;
import com.nishtahir.alang.value.StringValueTest;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created by tsotne on 12/6/15.
 */
@RunWith(Suite.class)
@SuiteClasses({ StringValueTest.class, IntegerValueTest.class, StringUtilsTest.class })
public class AllTest {
}

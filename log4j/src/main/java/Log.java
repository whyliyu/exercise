/**
 * @(#)Log.java, 2017/11/8.
 * <p/>
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import org.apache.log4j.Logger;

public class Log {
    static Logger logger = Logger.getLogger(Log.class );
    public static void main(String[] args) {
        logger.info("warn");
        System.out.println(logger.getName());
    }
}

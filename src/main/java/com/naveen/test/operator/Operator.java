package com.naveen.test.operator;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 7/3/11
 * Time: 8:46 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Operator {
    LESS_THAN {
        @Override
        public String value() {
            return "$lt";
        }
    },
    GREATER_THAN {
        @Override
        public String value() {
            return "$gt";
        }
    },
    LESS_THAN_OR_EQUAL{
        @Override
        public String value() {
            return "$lte";
        }
    },
    GREATER_THAN_OR_EQUAL{
        @Override
        public String value() {
            return "$gte";
        }
    },
    NOT_EQUAL{
        @Override
        public String value() {
            return "$ne";
        }
    },
    IN{
        @Override
        public String value() {
            return "$in";
        }
    },
    NOT_IN{
        @Override
        public String value() {
            return "$nin";
        }
    },
    LIKE{
        @Override
        public String value() {
            return "$regex";
        }
    },
    NOT_LIKE{
        @Override
        public String value() {
            return "$not";
        }
    };

    public abstract String value();
}

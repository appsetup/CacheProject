package com.naveen.test.query;

/**
 * Created by IntelliJ IDEA.
 * User: naveen
 * Date: 15/3/11
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Query
{
    private static final String FLOWER_BRACE_OPEN = "{";
    private static final String FLOWER_BRACE_CLOSE = "}";
    private static final String DELIMITER = ":";
    private static final String SQUARE_BRACE_OPEN = "[";
    private static final String SQAURE_BRACE_CLOSE = "]";
    private StringBuilder stringBuilder = new StringBuilder();

    public Query field(String column)
    {
        applyCommaIfNecessary();
        stringBuilder.append(FLOWER_BRACE_OPEN).append("\""+column+"\"").append(DELIMITER);
        return this;
    }

    public Query equalTo(String value)
    {
        stringBuilder.append(value).append(FLOWER_BRACE_CLOSE);
        return this;
    }

    private void applyCommaIfNecessary() {
        if(stringBuilder.length() != 0)
        {
            stringBuilder.append(",");
        }
    }

    public Query greaterThan(String value)
    {
        updateQueryString(value, "$gt");
        return this;
    }

    public Query greaterThanOrEqualTo(String value)
    {
        updateQueryString(value, "$gte");
        return this;
    }

    public Query lessThan(String value)
    {
        updateQueryString(value, "$lt");
        return this;
    }

    public Query lessThanOrEqualTo(String value)
    {
        updateQueryString(value, "$lte");
        return this;
    }

    public Query like(String value)
    {
        stringBuilder.append("/" + value + "/");
        return this;
    }

    public Query startsWith(String value)
    {
        stringBuilder.append("/^" + value + "/");
        return this;
    }

    public Query in(String value)
    {
        updateQueryString(SQUARE_BRACE_OPEN +value+ SQAURE_BRACE_CLOSE,"$in");
        return this;
    }

    public Query all(String value)
    {
        updateQueryString(SQUARE_BRACE_OPEN +value+ SQAURE_BRACE_CLOSE,"$all");
        return this;
    }

    public Query notIn(String value)
    {
        updateQueryString(SQUARE_BRACE_OPEN +value+ SQAURE_BRACE_CLOSE,"$nin");
        return this;
    }

    public Query or(Query... queries)
    {
        if(queries == null || queries.length <2)
        {
            throw new IllegalArgumentException("Method 'or' needs minimum of two subqueries for forming a " +
                    "valid 'or' condition");
        }
        applyCommaIfNecessary();
        StringBuilder orStringBuilder = new StringBuilder();
        for (Query query : queries) {
            orStringBuilder.append(query.getQuery()).append(",");
        }
        orStringBuilder = orStringBuilder.deleteCharAt(orStringBuilder.lastIndexOf(","));
        updateQueryString(SQUARE_BRACE_OPEN +orStringBuilder+ SQAURE_BRACE_CLOSE, "$or");
        return this;
    }
    private void updateQueryString(String value, String operator) {
        stringBuilder.append(FLOWER_BRACE_OPEN).append("\""+operator+"\"").append(DELIMITER).append(value).
                append(FLOWER_BRACE_CLOSE).
                append(FLOWER_BRACE_CLOSE);
    }

    public String getQuery()
    {
        return stringBuilder.toString();
    }
}

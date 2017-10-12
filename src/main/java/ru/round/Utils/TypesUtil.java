package ru.round.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

public class TypesUtil
{
    public static final String DIGITAL_CHARS = "0123456789";

    public static String asStr(Object val)
    {
        return asStr(val, "");
    }

    public static String asStr(Object val, String nullString)
    {
        String res = null;
        if (val == null) {
            res = nullString;
        } else {
            res = val.toString();
        }
        return res;
    }

    public static int asInt(Object val)
    {
        return (int)asLong(val);
    }

    public static long asLong(Object val)
    {
        long ret = 0L;
        try
        {
            if (((val instanceof Long)) || ((val instanceof Integer)))
            {
                ret = ((Number)val).longValue();
            }
            else if (val != null)
            {
                String str = val.toString().trim();
                if (str.length() > 0) {
                    if (!str.startsWith("+")) {
                        ret = Long.parseLong(str);
                    } else {
                        ret = Long.parseLong(str.substring(1));
                    }
                }
            }
        }
        catch (Throwable ex)
        {
            ret = 0L;
        }
        return ret;
    }

    public static float asFloat(Object val)
    {
        float ret = 0.0F;
        if (((val instanceof Float)) || ((val instanceof Double))) {
            ret = (float)((Number)val).doubleValue();
        } else if (val != null) {
            ret = Float.parseFloat(val.toString());
        }
        return ret;
    }

    public static double asDouble(Object val)
    {
        double ret = 0.0D;
        if (((val instanceof Float)) || ((val instanceof Double))) {
            ret = ((Number)val).doubleValue();
        } else if (val != null) {
            ret = Double.parseDouble(val.toString());
        }
        return ret;
    }

    public static BigDecimal asBigDec(Object val)
    {
        BigDecimal bd = null;
        try
        {
            if (val != null)
            {
                if ((val instanceof BigDecimal)) {
                    bd = (BigDecimal)val;
                } else if ((val instanceof BigInteger)) {
                    bd = new BigDecimal((BigInteger)val);
                } else {
                    bd = new BigDecimal(val.toString());
                }
            }
            else {
                bd = new BigDecimal(BigInteger.ZERO);
            }
        }
        catch (Throwable ex)
        {
            bd = new BigDecimal(BigInteger.ZERO);
        }
        return bd;
    }


    public static byte asByte(Object val)
    {
        byte valByte = (byte)asShort(val);
        return valByte;
    }

    public static short asShort(Object val)
    {
        short valShort = (short)(int)asLong(val);
        return valShort;
    }

    public static java.sql.Date toSqlDate(Object obj)
    {
        java.sql.Date sqlDate = null;
        if ((obj instanceof java.sql.Date)) {
            sqlDate = (java.sql.Date)obj;
        } else if ((obj instanceof java.util.Date)) {
            sqlDate = new java.sql.Date(((java.util.Date)obj).getTime());
        } else if (obj != null) {
            sqlDate = new java.sql.Date(toDate(obj.toString(), null).getTime());
        }
        return sqlDate;
    }

    public static java.util.Date toDate(Object obj)
    {
        java.util.Date date = null;
        if ((obj instanceof java.sql.Date)) {
            date = (java.util.Date)obj;
        } else if ((obj instanceof java.util.Date)) {
            date = (java.util.Date)obj;
        } else if (obj != null) {
            date = toDate(obj.toString(), null);
        }
        return date;
    }

    public static java.sql.Date toSqlDate(String strDate)
    {
        java.sql.Date sqlDate = new java.sql.Date(toDate(strDate, null).getTime());
        return sqlDate;
    }

    public static java.sql.Date toSqlDate(String strDate, String[] format)
    {
        java.sql.Date sqlDate = new java.sql.Date(toDate(strDate, format).getTime());
        return sqlDate;
    }

    public static java.util.Date toDate(int year, int month, int day)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setGregorianChange(new GregorianCalendar(1918, 1, 14).getTime());
        calendar.set(year, month - 1, day);
        return new java.util.Date(calendar.getTime().getTime());
    }

    public static java.util.Date toDate(String strDate, String[] format)
    {
        StringBuffer sbFormat = null;

        int part = 0;
        int year = 1918;int month = 2;int day = 14;

        int len = 0;
        int pos = 0;

        char[] chars = null;
        if (strDate == null) {
            return toDate(year, month, day);
        }
        try
        {
            sbFormat = new StringBuffer();
            if ((format != null) && (format.length != 0)) {
                sbFormat.append(format[0].trim());
            }
            if (sbFormat.length() != 3)
            {
                sbFormat.setLength(0);
                sbFormat.append("ymd");
            }
            StringBuffer sbYear = new StringBuffer(4);
            StringBuffer sbMonth = new StringBuffer(2);
            StringBuffer sbDay = new StringBuffer(2);

            chars = strDate.trim().toCharArray();
            len = chars.length;
            char chIndex = ' ';
            while (pos < len)
            {
                if ("0123456789".indexOf(chars[pos]) >= 0)
                {
                    chIndex = ' ';
                    if (part == 0) {
                        chIndex = sbFormat.charAt(part);
                    } else if (part == 1) {
                        chIndex = sbFormat.charAt(part);
                    } else if (part == 2) {
                        chIndex = sbFormat.charAt(part);
                    }
                    switch (chIndex)
                    {
                        case 'd':
                            sbDay.append(chars[pos]);
                            break;
                        case 'm':
                            sbMonth.append(chars[pos]);
                            break;
                        case 'y':
                            sbYear.append(chars[pos]);
                    }
                }
                else
                {
                    part++;
                }
                if (part > 2) {
                    break;
                }
                pos++;
            }
            year = asInt(sbYear.toString());
            month = asInt(sbMonth.toString());
            day = asInt(sbDay.toString());
            if ((month < 1) || (month > 12)) {
                throw new Exception("Error 1-12");
            }
        }
        catch (Throwable ex)
        {
            year = 1918;
            month = 2;
            day = 14;
        }
        return toDate(year, month, day);
    }

    public static String replaceString(String str, String from, String to)
    {
        StringBuffer sb = new StringBuffer(str);
        int index = 0;
        if ((from == null) || (to == null)) {
            return str;
        }
        if (from.equals(to)) {
            return str;
        }
        while ((index = sb.indexOf(from, index)) >= 0) {
            sb.replace(index, index + from.length(), to);
        }
        return sb.toString();
    }
}

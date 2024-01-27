package com.nhd.management.utils;

import java.util.List;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public abstract class ZzCheckUtils {
  /**
   * 日付の正規表現(yyyy/MM/dd形式)
   */
  protected static final Pattern PTN_YYYY_MM_DD = Pattern.compile("^\\d{4}/\\d{1,2}/\\d{1,2}$");

  /**
   * 日付の正規表現(yyyyMMdd形式)
   */
  protected static final Pattern PTN_YYYYMMDD = Pattern.compile("^([0-9]{4})([0-9]{2})([0-9]{2})$");

  /**
   * 時刻の正規表現(HH:mm:ss形式)
   */
  protected static final Pattern PTN_HH_MM_SS = Pattern.compile("^\\d{1,2}:\\d{1,2}:\\d{1,2}$");

  /**
   * 時刻の正規表現(HHmmss形式)
   */
  protected static final Pattern PTN_HHMMSS = Pattern.compile("^([0-9]{2})([0-9]{2})([0-9]{2})$");

  /**
   * 時分の正規表現(HH:mm形式)
   */
  protected static final Pattern PTN_HH_MM = Pattern.compile("^\\d{1,2}:\\d{1,2}$");

  /**
   * 時分の正規表現(HHmm形式)
   */
  protected static final Pattern PTN_HHMM = Pattern.compile("^([0-9]{2})([0-9]{2})$");

  /**
   * 数字の正規表現
   */
  protected static final Pattern PTN_NUMBER = Pattern.compile("^[0-9]+$");

  /**
   * 英字の正規表現
   */
  protected static final Pattern PTN_ALPHA = Pattern.compile("^[a-zA-Z]+$");

  /**
   * 半角英数字の正規表現
   */
  protected static final Pattern PTN_ALPHA_NUMBER = Pattern.compile("^[0-9a-zA-Z]+$");

  /**
   * 日時の正規表現(yyyy/MM/dd HH:mm:ss形式)
   */
  protected static final Pattern PTN_DATE_TIME =
      Pattern.compile("^\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$");

  /**
   * 時間数の正規表現(HHHHH…:mm:ss形式)
   */
  protected static final Pattern PTN_TIME_SU = Pattern.compile("^\\d{1,6}:[0-5][0-9]:[0-5][0-9]$");

  /**
   * 半角英字(大文字)の正規表現
   */
  protected static final Pattern PTN_ALPHA_CAPITAL = Pattern.compile("^[A-Z]+$");

  /**
   * 半角英字(小文字)の正規表現
   */
  protected static final Pattern PTN_ALPHA_SMALL = Pattern.compile("^[a-z]+$");

  /**
   * メールアドレスのアカウント部に利用可能な文字の正規表現
   *
   * 連続する"."が許されているケース等を考慮して、特殊な条件下でのみ使用可能な文字であっても無条件に利用可能な文字と判定します。
   * 許可する文字(RFC5322参照)：小・大文字のアルファベット、数字、半角スペース、右記記号 !#$%&'*+-/=?^_`{|}~()<>[]:;@,.\"
   */
  protected static final Pattern PTN_MAILADDRESS_ACCOUNT = Pattern
      .compile("^[a-zA-Z0-9 !#\\$%&'\\*\\+\\-/=\\?\\^_`\\{\\|\\}~\\(\\)<>\\[\\]:;@,\\.\\\\\"]+$");

  /**
   * 
   */
  public ZzCheckUtils() {}

  /**
   * Checks if the value is empty.
   * 
   * @param value
   * @return true: empty false: not empty
   */
  public static boolean isEmpty(String value) {
    return StringUtils.hasText(value);
  }

  /**
   * Checks if the value is empty.
   * 
   * @param value
   * @return true: empty false: not empty
   */
  public static boolean isEmpty(Object value) {
    if (null == value) {
      return true;
    } else if (value instanceof List) {
      @SuppressWarnings("rawtypes")
      List list = (List) value;
      if (list.size() == 0) {
        return true;
      } else {
        return false;
      }
    } else if (value instanceof String) {
      return StringUtils.hasText((String) value);
    } else {
      return false;
    }
  }
}

package com.sofa.client.core.utils;

import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.function.Function;

/**
 * @author: TangFenQi
 * @description:
 * @date：2020/1/2 16:51
 */
public class HttpUtils {

  private static Gson gson = new Gson();

  public static <T> HttpResponse<T> get(HttpRequest request, Class<T> type) {
    Function<String, T> function = s -> gson.fromJson(s, type);
    return get(request, function);
  }

  public static <T> HttpResponse<T> get(HttpRequest request, Type type) {
    Function<String, T> function = s -> gson.fromJson(s, type);
    return get(request, function);
  }

  private static <T> HttpResponse<T> get(HttpRequest request, Function<String, T> function) {
    HttpURLConnection urlConnection;
    InputStreamReader inputStreamReader = null;
    int responseCode;
    try {
      urlConnection = (HttpURLConnection) new URL(request.getUrl())
          .openConnection();
      urlConnection.setRequestMethod("GET");
      if (request.getConnectTimeOut() != null) {
        urlConnection.setConnectTimeout(request.getConnectTimeOut());
      }
      if (request.getReadTimeOut() != null) {
        urlConnection.setReadTimeout(request.getReadTimeOut());
      }

      urlConnection.connect();
      responseCode = urlConnection.getResponseCode();
      String responseBody;
      inputStreamReader = new InputStreamReader(urlConnection.getInputStream(),
          "UTF-8");
      responseBody = CharStreams.toString(inputStreamReader);
      return new HttpResponse(function.apply(responseBody), responseCode);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStreamReader != null) {
        try {
          inputStreamReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        ;
      }
    }
    return null;
  }

}

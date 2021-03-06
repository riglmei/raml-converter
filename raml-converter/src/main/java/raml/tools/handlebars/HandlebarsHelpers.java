package raml.tools.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import org.raml.model.SecurityReference;

import java.io.IOException;
import java.util.List;

public class HandlebarsHelpers {
  public static Helper<Object> lowerCaseHelper() {
    return new Helper<Object>() {

      @Override
      public CharSequence apply(Object s, Options options) throws IOException {
        if (s != null) {
          return s.toString().toLowerCase();
        }
        return "";
      }
    };
  }

  public static Helper<Object> highlightHelper() {
    return new Helper<Object>() {
      @Override
      public CharSequence apply(Object o, Options options) throws IOException {
        return o.toString();
      }
    };
  }

  public static Helper<Object> preOrLink() {
    return new Helper<Object>() {
      @Override
      public CharSequence apply(Object o, Options options) throws IOException {
        if (o == null) {
          return "";
        }
        if (o.toString().trim().startsWith("{")) {
          return new Handlebars.SafeString("<pre><code class=\"json\">" + o.toString() + "</code></pre>");
        } else {
          return new Handlebars.SafeString(String.format("<a href=\"#%s\" >%s</a>", o.toString(), o.toString()));
        }
      }
    };
  }

  public static Helper<List<SecurityReference>> lockHelper() {
    return new Helper<List<SecurityReference>>() {
      @Override
      public CharSequence apply(List<SecurityReference> securityReferences, Options options) throws IOException {
        if (!securityReferences.isEmpty()) {
          return "<span class=\"glyphicon glyphicon-lock\" title=\"Authentication required\"></span>";
        }
        return "";
      }
    };
  }
}

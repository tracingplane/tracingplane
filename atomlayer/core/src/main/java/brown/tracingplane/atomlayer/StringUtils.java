package brown.tracingplane.atomlayer;

/**
 * Want to avoid having apache commons as a dependency, so implement any functions used here
 */
public class StringUtils {

    public static String join(Iterable<?> objects, String separator) {
        if (objects == null) return "";
        StringBuilder b = new StringBuilder();
        boolean first = true;
        for (Object s : objects) {
            if (!first) {
                b.append(separator);
            } else {
                first = false;
            }
            b.append(String.valueOf(s));
        }
        return b.toString();
    }

}

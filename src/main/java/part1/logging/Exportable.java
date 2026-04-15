package part1.logging;

import java.io.OutputStream;

public interface Exportable {
    /**
     * Exports content to the given OutputStream
     * @param out The OutputStream to export to
     */
    void exportTo(OutputStream out);
}

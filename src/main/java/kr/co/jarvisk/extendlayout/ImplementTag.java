package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public abstract class ImplementTag extends SimpleTagSupport {

    private Writer writer;

    private int depth;

    public ImplementTag() {
        writer = new StringWriter();
    }

    public Writer getWriter() {
        return writer;
    }

    @Override
    public void doTag() throws JspException, IOException {
        init();

        write();
    }

    protected abstract void write() throws JspException, IOException;

    private void init() {
        LayoutHelper.setDepth(getJspContext());
        depth = LayoutHelper.getDepth(getJspContext());
    }

    public int getDepth() {
        return depth;
    }

}

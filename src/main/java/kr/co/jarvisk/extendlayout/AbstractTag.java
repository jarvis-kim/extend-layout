package kr.co.jarvisk.extendlayout;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.Writer;

/***
 * 추상 태그의 부모 클래스
 */
public abstract class AbstractTag extends SimpleTagSupport {

    private int depth;

    @Override
    public void doTag() throws JspException, IOException {
        init();
        renderChild();
    }

    private void init() {
        LayoutHelper.setDepth(getJspContext());
        depth = LayoutHelper.getDepth(getJspContext());
    }

    public int getDepth() {
        return depth;
    }

    protected abstract void renderChild() throws JspException, IOException;

    protected boolean hasParentOfImplement() {
        JspTag tag = getParent();

        while ( tag != null ) {
            if ( getImplementClass().isAssignableFrom(tag.getClass()) ) {
                return true;
            }

            if ( tag instanceof SimpleTag ) {
                tag = ((SimpleTag) tag).getParent();
            } else if ( tag instanceof BodyTag ) {
                tag = ((BodyTag) tag).getParent();
            } else {
                tag = null;
            }
        }

        return false;
    }

    protected abstract Class<? extends ImplementTag> getImplementClass();


    protected Writer getWriter() {
        if ( hasParentOfImplement() ) {
            return getParentWriter();
        }

        return getJspContext().getOut();
    }

    protected Writer getParentWriter() {
        ImplementTag parentTag = getParentImplementTag();
        if ( parentTag == null ) {
            return null;
        }

        return parentTag.getWriter();

    }

    protected ImplementTag getParentImplementTag() {
        JspTag tag = getParent();

        while ( tag != null ) {
            if ( getImplementClass().isAssignableFrom(tag.getClass()) ) {
                return (ImplementTag) tag;
            }

            if ( tag instanceof SimpleTag ) {
                tag = ((SimpleTag) tag).getParent();
            } else if ( tag instanceof BodyTag ) {
                tag = ((BodyTag) tag).getParent();
            } else {
                tag = null;
            }
        }

        return null;
    }


    protected abstract Writer getChildWriter();

}

package com.brandonhessler.Attenuator;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Created by Bhessler on 1/28/2015.
 */
public class RegExFieldFormatRestrictor extends PlainDocument {
    private static final long serialVersionUID = 714707460508829881L;
    /**
     * The regular expression the text field must conform to
     */
    private String regex;

    /**
     * Will be set to false if the user enters characters that are invalid given the regex
     */
    private boolean valid = true;

    /**
     * If true, the user will be unable to enter characters that are contrary to the regex.
     * If false, the user will be able to enter characters and the invalid bit will be set to true;
     */
    private boolean allowInvalid;

    /**
     *
     * @param regex The regular expression the text field must conform to
     * @param allowInvalid determines whether the user will be able to enter an invalid string or be blocked from doing so
     */
    public RegExFieldFormatRestrictor(String regex, boolean allowInvalid) {
        super();
        this.regex = regex;
        this.allowInvalid = allowInvalid;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        String oldString = this.getContent().getString(0, getLength());
        String newString = oldString.substring(0, offs) + str +
                ((oldString.length() - 1 < offs) ? "" : oldString.substring(offs+1, oldString.length()));

        if(str == null)
            return;

        if(!newString.matches(regex)){
            valid = false;

            if(!allowInvalid)
                return;
        }else{
            valid = true;
        }

        super.insertString(offs, str, a);
    }

}

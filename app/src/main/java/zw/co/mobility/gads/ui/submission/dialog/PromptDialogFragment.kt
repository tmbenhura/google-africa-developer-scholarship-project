package zw.co.mobility.gads.ui.submission.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import zw.co.mobility.gads.R

interface PromptDialogCallback {
    fun onAccept()
    fun onClose()
}

class PromptDialogFragment(@StringRes val titleRes: Int, val callback: PromptDialogCallback) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.dialog_prompt, container, false) as ViewGroup
        val prompt = layout.findViewById<TextView>(R.id.prompt)
        prompt.setText(titleRes)

        val close = layout.findViewById<View>(R.id.close)
        close.setOnClickListener {
            callback.onClose()
        }

        val accept = layout.findViewById<View>(R.id.accept)
        accept.setOnClickListener {
            callback.onAccept()
        }

        return layout
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}
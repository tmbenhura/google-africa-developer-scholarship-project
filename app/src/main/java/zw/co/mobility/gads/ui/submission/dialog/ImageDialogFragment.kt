package zw.co.mobility.gads.ui.submission.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import zw.co.mobility.gads.R

class ImageDialogFragment(@DrawableRes val imageRes: Int, @StringRes val titleRes: Int) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.dialog_image_with_prompt, container, false) as ViewGroup
        val image = layout.findViewById<ImageView>(R.id.image)
        image.setImageDrawable(ContextCompat.getDrawable(image.context, imageRes))

        val prompt = layout.findViewById<TextView>(R.id.prompt)
        prompt.setText(titleRes)

        return  layout
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}
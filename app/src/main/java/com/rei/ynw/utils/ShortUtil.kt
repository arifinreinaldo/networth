package com.rei.ynw.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.CountDownTimer
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.rei.ynw.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun write(value: String) {
//    if (BuildConfig.DEBUG) {
//        Log.d(getTAG(), value)
//    }
}

fun hideKeyboard(context: Context?, view: View?) {
    context?.let { ctx ->
        val imm = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view?.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}

fun showKeyboard(context: Context?, view: View?) {
    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, 0)
}

fun Button.disableFunction(ctx: Context) {
    this.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorPrimaryDark))
    this.isEnabled = false
}

fun Button.enableFunction(ctx: Context) {
    this.setBackgroundColor(ContextCompat.getColor(ctx, R.color.colorAccent))
    this.isEnabled = true
}

fun confirmDialog(
    ctx: Context,
    title: String,
    positive: String,
    negative: String,
    posFun: () -> Unit
): AlertDialog {
    return AlertDialog.Builder(ctx).setTitle(title)
        .setPositiveButton(positive) { _, _ ->
            posFun()
        }
        .setNegativeButton(negative) { dialog, _ -> dialog.dismiss() }
        .create()
}

fun View.invisView() {
    this.visibility = View.INVISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.isDisplayed(boolean: Boolean) {
    if (boolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun ImageView.load(value: Any) {
    val options = RequestOptions().error(R.drawable.ic_launcher_background)
    Glide.with(this).setDefaultRequestOptions(options).load(value).into(this)
}

fun ImageView.loadProfile(value: Any, image: Drawable?) {
    Glide.with(this).load(value).circleCrop().error(
        image
    ).into(this)
}

fun RecyclerView.init(
    ctx: Context,
    type: String = "linear",
    horizontal: Boolean = false,
    reverseLayout: Boolean = false,
    column: Int = 2
) {
    if (type.equals("linear", true)) {
        if (horizontal) {
            this.layoutManager =
                LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, reverseLayout)
        } else {
            this.layoutManager =
                LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, reverseLayout)
        }
    } else if (type.equals("grid", true)) {
        this.layoutManager = GridLayoutManager(context, column)
    } else {
    }
}

fun simpleCountDown(
    seconds: Double,
    interval: Double,
    finish: () -> Unit,
    tick: () -> Unit
): CountDownTimer {
    return object : CountDownTimer(seconds.toMilis(), interval.toMilis()) {
        override fun onFinish() {
            this.cancel()
            finish()
        }

        override fun onTick(millisUntilFinished: Long) {
            tick()
        }

    }
}

fun Double.toMilis(): Long {
    return (this * 1000).toLong()
}

fun setReadonly(editText: EditText) {
    editText.inputType = InputType.TYPE_NULL
    editText.setTextIsSelectable(false)
}

fun setReadonly(list: List<EditText>) {
    list.forEach {
        setReadonly(it)
    }

}

fun parseDate(value: String, format: String): Date {
    val formatter = SimpleDateFormat(format)
    return formatter.parse(value)
}

fun printDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format)
    return formatter.format(date)
}

fun convert(dateBefore: String, formatBefore: String, formatAfter: String): String {
    return printDate(parseDate(dateBefore, formatBefore), formatAfter)
}

fun SwipeRefreshLayout.init(ctx: Context) {
    this.setColorSchemeColors(
        ContextCompat.getColor(ctx, R.color.colorAccent),
        ContextCompat.getColor(ctx, R.color.colorPrimary),
        ContextCompat.getColor(ctx, R.color.colorPrimaryDark)
    )
}

fun formatCurrency(value: Int): String {
    val local = Locale("in", "ID")
    val format = NumberFormat.getCurrencyInstance(local)
    return format.format(value.toDouble())
}


fun Activity.hasPermission(vararg permissions: String): Boolean {
    var all_approved = true
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        for (permission in permissions) {
            if (!this.hasPermission(permission)) {
                all_approved = false
            }
        }
    }
    return all_approved
}

fun Activity.hasPermission(permission: String): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (ActivityCompat.checkSelfPermission(
                this,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
    }
    return true
}

fun Activity.askPermission(
    permission: String = "",
    request: Int
) {
    if (!permission.isNullOrEmpty()) {
        ActivityCompat.requestPermissions(this, arrayOf(permission), request)
    }
}

fun Activity.askPermission(
    permissions: Array<String>,
    request: Int
) {
    if (permissions.isNotEmpty()) {
        ActivityCompat.requestPermissions(this, permissions, request)
    }
}

fun EditText?.isEmpty(): Boolean {
    return this?.text.isNullOrEmpty()
}

fun EditText?.extract(): String {
    return this?.text.toString().trim()
}

fun EditText?.extractNumber(): Int {
    var x = this?.text.toString().trim()
    if (x.isEmpty()) {
        x = "0"
    }
    return x.toInt()
}

fun getDrawable(image: Int, ctx: Context): Drawable {
    return ctx.getDrawable(image)!!
}


fun String.displayDate(): String {
    if (!this.isNullOrEmpty()) {
        return convert(this, Constants.format_date_save, Constants.format_date)
    } else {
        return "";
    }
}

fun String.displayTime(): String {
    return convert(this, Constants.format_time_save, Constants.format_time)
}

fun scrollListener(scroll: () -> Unit): EndlessScrollUtil {
    return object : EndlessScrollUtil() {
        override fun onLoadMore() {
            scroll()
        }
    }
}

fun nullParent(): ViewGroup? {
    return null
}

fun TextView.strikeThrough() {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

fun stripZeros(any: Any): String {
    var s = any.toString()
    s = if (s.indexOf(".") < 0) s else s.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
    return s
}

fun snackDisplay(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}
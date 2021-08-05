package com.safekiddo.exercise.presentation.ui.post_edit

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.safekiddo.exercise.R
import com.safekiddo.exercise.domain.model.Post
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostEditFragment : Fragment() {

    private val viewModel: PostEditViewModel by viewModels()
    private lateinit var editImageButton: Button
    private lateinit var updateButton: Button
    private lateinit var cancelButton: Button
    private lateinit var imagePreview: ImageView
    private lateinit var titleEdit: EditText
    private lateinit var descriptionEdit: EditText
    private lateinit var selectedImage: Uri
    private lateinit var post: Post
    private val SELECT_PICRUTE = 700

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.post_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        post = arguments?.getParcelable("post")!!
        selectedImage = Uri.parse(post.featuredImage)
        initButtons()
        initImageViews()
        initEditTexts()
        Picasso.get().load(post.featuredImage.replace("https", "http").replace("http", "https")).into(imagePreview)

        editImageButton.setOnClickListener{ setImage() }
        cancelButton.setOnClickListener { onCancelAction() }
        updateButton.setOnClickListener { onUpdateAction() }

    }

    private fun onUpdateAction() {

        if(titleEdit.text.isNotBlank()){
            post.title = titleEdit.text.toString()
        }

        if(descriptionEdit.text.isNotBlank()){
            post.description = descriptionEdit.text.toString()
        }

        post.featuredImage = selectedImage.toString()

        viewModel.updatePost(post)

        val bundle: Bundle = Bundle()
        bundle.putParcelable("post", post)
        findNavController().popBackStack(R.id.postListFragment, false)
        findNavController().navigate(R.id.showPostAction, bundle)
    }

    private fun setImage() {
        val intent: Intent = Intent()
        intent.action = Intent.ACTION_OPEN_DOCUMENT
        intent.type = "image/*"
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_PICRUTE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == SELECT_PICRUTE){
                selectedImage = data!!.data!!
                context?.contentResolver?.takePersistableUriPermission(selectedImage, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                imagePreview.setImageURI(selectedImage)
            }
        }
    }

    private fun onCancelAction() {
        findNavController().popBackStack(R.id.postListFragment, false)
    }

    private fun initButtons(){
        editImageButton = view?.findViewById(R.id.edit_image_button)!!
        updateButton = view?.findViewById(R.id.edit_button_update)!!
        cancelButton = view?.findViewById(R.id.edit_button_cancel)!!
    }

    private fun initImageViews(){
        imagePreview = view?.findViewById(R.id.edit_post_icon)!!
    }

    private fun initEditTexts(){
        titleEdit = view?.findViewById(R.id.edit_post_title)!!
        descriptionEdit = view?.findViewById(R.id.edit_post_description)!!
    }

}
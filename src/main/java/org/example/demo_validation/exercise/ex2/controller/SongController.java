package org.example.demo_validation.exercise.ex2.controller;

import jakarta.validation.Valid;
import org.example.demo_validation.exercise.ex2.model.Song;
import org.example.demo_validation.exercise.ex2.service.HibernateSongService;
import org.example.demo_validation.exercise.ex2.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/exercise/ex2/songs")
public class SongController {
    private final SongService songService = new HibernateSongService();

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "/exercise/ex2/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("song", new Song());
        return "/exercise/ex2/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Song song, BindingResult bindingResult, @RequestParam("file") MultipartFile file) throws IOException {
        try {
            if (bindingResult.hasErrors()) {
                return "/exercise/ex2/create"; // quay lại form và hiện lỗi
            }

            String uploadDir = "C:\\Users\\duytr\\Downloads\\upload\\";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            song.setFilePath(file.getOriginalFilename());
            songService.save(song);
            System.out.println("Saved file at: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/exercise/ex2/songs";
    }

    @GetMapping("/play/{id}")
    public String play(@PathVariable("id") long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "/exercise/ex2/play";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        songService.delete(id);
        return "redirect:/exercise/ex2/songs";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "/exercise/ex2/edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute Song song, BindingResult bindingResult,@RequestParam("file") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors()) {
            return "/exercise/ex2/edit";
        }
        if (!file.isEmpty()) {
            String uploadDir = "C:\\Users\\duytr\\Downloads\\upload\\";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) uploadFolder.mkdirs();

            String filePath = uploadDir + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            song.setFilePath(file.getOriginalFilename());
        } else {
            Song oldSong = songService.findById(song.getId());
            song.setFilePath(oldSong.getFilePath());
        }
        songService.update(song);
        return "redirect:/exercise/ex2/songs";
    }
}
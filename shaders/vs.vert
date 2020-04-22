#version 450

in vec2 position;

in vec2 texPos;
out vec2 TexPos;

uniform mat4 model;
uniform mat4 view;
uniform mat4 projection;

void main() {
	gl_Position = projection * projection * vec4(position, 0, 1);
	TexPos = texPos;
}

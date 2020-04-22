#version 450

out vec4 FragColor;

in vec2 TexPos;

uniform sampler2D sampler;

void main() {
	FragColor = texture(sampler, TexPos);
}
